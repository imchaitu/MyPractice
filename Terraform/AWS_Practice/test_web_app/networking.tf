module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = "twa-vpc"
  cidr = var.test_web_app_cidr

  azs             = data.aws_availability_zones.test_web_app_azs.names
  private_subnets = slice(local.test_web_app_subnets, 0, 2)
  public_subnets  = slice(local.test_web_app_subnets, 2, 4)


  enable_nat_gateway = true
  single_nat_gateway = true
  enable_vpn_gateway = false

  tags = {
    Terraform   = "true"
    Environment = "dev"
    Project     = "test-web-app"
  }
}

module "public_ssh_sg" {
  source = "terraform-aws-modules/security-group/aws"

  name        = "twa-ssh-public-sg"
  description = "Security group for bastion server"
  vpc_id      = module.vpc.vpc_id

  ingress_cidr_blocks = ["0.0.0.0/0"]
  ingress_rules       = ["ssh-tcp"]

  # Outbound to private EC2s
  egress_with_cidr_blocks = [
    {
      from_port = 0
      to_port = 0
      protocol = -1
      cidr_blocks = module.vpc.vpc_cidr_block
    }
  ]
}

module "lb_http_sg" {
  source = "terraform-aws-modules/security-group/aws"

  name        = "twa-lb-http-public"
  description = "Security group for public load balancer."
  vpc_id      = module.vpc.vpc_id

  ingress_cidr_blocks = ["0.0.0.0/0"]
  ingress_rules       = ["http-80-tcp"]

  egress_with_cidr_blocks = [
    {
      from_port = 0
      to_port = 0
      protocol = -1
      cidr_blocks = "0.0.0.0/0"
    }
  ]
}

module "private_sg" {
  source = "terraform-aws-modules/security-group/aws"

  name        = "twa-lb-asg-sg"
  description = "Security group from LB to ASG"
  vpc_id      = module.vpc.vpc_id

  ingress_with_source_security_group_id = [
    {
      from_port                = 80
      to_port                  = 80
      protocol                 = "tcp"
      source_security_group_id = module.lb_http_sg.security_group_id
    },
    {
      from_port = 22
      to_port = 22
      protocol = "tcp"
      source_security_group_id = module.public_ssh_sg.security_group_id
    }
  ]

  egress_with_cidr_blocks = [
    {
      from_port = 0
      to_port = 0
      protocol = -1
      cidr_blocks = "0.0.0.0/0"
    }
  ]
}