# Variables
# #########
variable "aws_region" {
    type = String
    description = "The aws region that is used for the resources here"
    default = "us-east-1"
}

# Terraform block
terraform {
  required_providers {
    aws = {
        source = "hashicorp/aws"
        version = "5.72.1"
    }
  }
}

# Resources
provider "aws" {
  profile = "aws2myadmin"
  region = var.aws_region
}

