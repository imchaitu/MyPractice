module "test_web_app_alb" {
  source = "terraform-aws-modules/alb/aws"

  vpc_id          = module.vpc.vpc_id
  subnets         = module.vpc.public_subnets
  security_groups = [module.lb_http_sg.security_group_id]
  enable_deletion_protection = false

  listeners = {
    http_in = {
      port = 80
      protocol = "HTTP"
      forward = {
        target_group_key = "test_web_app_tg"
      }
    }
  }

  target_groups = {
    test_web_app_tg = {
      name_prefix = "twatg-"
      protocol = "HTTP"
      port = 80
      target_type = "instance"
      load_balancing_cross_zone_enabled = true
      create_attachment = false
    }
  }

}

resource "aws_launch_template" "test_web_app_lt" {
  name_prefix = "twa-lt-"

  instance_type = "t2.micro"
  image_id      = "ami-06b21ccaeff8cd686"

  key_name = "my_practice"

  vpc_security_group_ids = [module.private_sg.security_group_id]

  user_data = filebase64("${path.module}/files/user_data.sh")
}

module "test_web_app_asg" {
  source = "terraform-aws-modules/autoscaling/aws"

  name = "test_web_app_asg"

  min_size         = 2
  max_size         = 5
  desired_capacity = 3

  health_check_type      = "EC2"
  vpc_zone_identifier    = module.vpc.private_subnets
  launch_template_id     = aws_launch_template.test_web_app_lt.id
  create_launch_template = false

  scaling_policies = {
    test-web-app-asg-scaling-policy = {
      policy_type = "TargetTrackingScaling"
      target_tracking_configuration = {
        predefined_metric_specification = {
          predefined_metric_type = "ASGAverageCPUUtilization"
        }
        target_value = 70.0
      }
    }
  }
}

resource "aws_autoscaling_attachment" "test_web_app_asg_tg_attach" {
  for_each = module.test_web_app_alb.target_groups

  autoscaling_group_name = module.test_web_app_asg.autoscaling_group_name
  lb_target_group_arn    = module.test_web_app_alb.target_groups[each.key].arn
}


module "twa_bastion_ec2" {
  source  = "terraform-aws-modules/ec2-instance/aws"

  name = "twa-bastion-ec2"

  instance_type = "t2.micro"
  key_name = "my_practice"
  subnet_id = module.vpc.public_subnets[0]
  vpc_security_group_ids = [module.public_ssh_sg.security_group_id]
  associate_public_ip_address = true
}