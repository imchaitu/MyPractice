locals {
  test_web_app_subnets = cidrsubnets(var.test_web_app_cidr, 8, 8, 8, 8)
}