module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = "hotel-vpc-manuel"
  cidr = "10.0.0.0/16"

  azs             = ["us-east-2a", "us-east-2b"]
  private_subnets = ["10.0.1.0/24"]
  public_subnets  = ["10.0.101.0/24"]

  enable_nat_gateway   = true
  enable_vpn_gateway   = true
  single_nat_gateway   = true
  enable_dns_support   = true
  enable_dns_hostnames = true


  tags = {
    Terraform   = "true"
    Environment = "dev"
  }
}

module "sg_hotel_app_https_http_ssh" {
  source = "terraform-aws-modules/security-group/aws"

  name        = "SG-Hotel-app_http_https_ssh"
  description = "Security group for dev-container"
  vpc_id      = module.vpc.vpc_id

  ingress_with_cidr_blocks = [
    {
      rule        = "https-443-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      rule        = "http-80-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      from_port   = 8081
      to_port     = 8081
      protocol    = "tcp"
      description = "User-service ports"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      rule        = "ssh-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
  ]
  egress_with_cidr_blocks = [
    {
      rule        = "https-443-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      rule        = "http-80-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      from_port   = 8081
      to_port     = 8081
      protocol    = "tcp"
      description = "User-service ports"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      rule        = "ssh-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      from_port   = 3306
      to_port     = 3306
      protocol    = "tcp"
      description = "Connect to mysql port"
      cidr_blocks = "0.0.0.0/0"
    },
  ]

  tags = {
    Name = "sg-dev-container-public-subnet"
  }
}
module "sg_hotel_app_ssh" {
  source = "terraform-aws-modules/security-group/aws"

  name        = "SG-Hotel-app_ssh"
  description = "Security group for database instance"
  vpc_id      = module.vpc.vpc_id

  ingress_with_source_security_group_id = [
    {
      from_port                = 3306
      to_port                  = 3306
      protocol                 = "tcp"
      description              = "mysql server port"
      source_security_group_id = module.sg_hotel_app_https_http_ssh.security_group_id
    },
    {
      rule                     = "ssh-tcp"
      source_security_group_id = module.sg_hotel_app_https_http_ssh.security_group_id
    },
  ]
  egress_with_cidr_blocks = [
    {
      rule        = "ssh-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      rule        = "all-all"
      cidr_blocks = "0.0.0.0/0"
    },
  ]

  tags = {
    Name = "sg-db-private-subnet"
  }
}
