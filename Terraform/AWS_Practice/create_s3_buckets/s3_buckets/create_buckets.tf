variable "bucket_suffixes" {
    type = list(string)
    description = "The suffixes that are used for each bucket"
    default = [ "cross-acc-read", "event-notification", "mfa-delete", "replication-srr-dest", "replication-src", "replicatio-crr-dest", "static-website", "objectlock", "versioning", "public", "acl", "nothing" ]
}

terraform {
  required_providers {
    aws = {
        source = "hashicorp/aws"
        configuration_aliases = [aws.child1, aws.main,]
    }
  }
}

resource "aws_s3_bucket" "backend_bucket" {
  bucket = "chaitu-main-terraform-backend"
  provider = aws.main
}

resource "aws_s3_bucket" "my_s3_buckets" {
    provider = aws.child1

    for_each = toset(var.bucket_suffixes)

    bucket = "chaitu-aws2-s3-practice-${each.value}"
    force_destroy = true
}
