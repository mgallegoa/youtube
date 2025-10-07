terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 6.0"
    }
  }

  backend "s3" {
    bucket       = "terraform.state.manuel.arias"
    region       = "us-east-2"
    encrypt      = true
    key          = "manuel/hotel/dev/ec2/terraform.tfstate"
    use_lockfile = true


  }

}
