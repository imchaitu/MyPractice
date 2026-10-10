# Variables
# #########
variable "aws_region" {
    type = string
    description = "The aws region that is used for the resources here"
    default = "us-east-1"
}

# Terraform block
terraform {
  backend "s3" {
    bucket = "chaitu-main-terraform-backend"
    key = "terraform/state/child1/terraform.tfstate"
    region = "us-east-1"
  }
  required_providers {
    aws = {
        source = "hashicorp/aws"
        version = "~> 5.72.1"
        configuration_aliases = [aws.main, aws.child1]
    }
  }
}

# Providers
provider "aws" {
  region = var.aws_region
  alias = "main"
}

provider "aws" {
  profile = "aws2myadmin"
  region = var.aws_region
  alias = "child1"
}

module "s3_buckets" {
  providers = {
    aws.main = aws.main
    aws.child1 = aws.child1
  }

  source = "./s3_buckets"
}

