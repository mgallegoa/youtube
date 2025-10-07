locals {
  ami-instance-us-east-2          = "ami-077b630ef539aa0b5"
  user-data-install-docker-amazon = <<-EOF
                            #!/bin/bash
                            yum update -y
                            yum install docker -y
                            systemctl enable docker
                            systemctl start docker
                            usermod -aG docker ec2-user
  EOF

}

data "terraform_remote_state" "network" {
  backend = "s3"
  config = {
    bucket = "terraform.state.manuel.arias"
    region = "us-east-2"
    key    = "manuel/network/terraform.tfstate"
  }
}

resource "aws_instance" "instance-public-subnet-dev-container" {
  ami                         = local.ami-instance-us-east-2
  instance_type               = "t3.small" # 2 GB Ram
  user_data                   = local.user-data-install-docker-amazon
  key_name                    = "ssh-key-AWS-Instance-us-east-2-user-manuelarias-2025-09-11"
  subnet_id                   = data.terraform_remote_state.network.outputs.public_subnets_ids[0]
  vpc_security_group_ids      = [data.terraform_remote_state.network.outputs.sg_dev_container.security_group_id]
  associate_public_ip_address = true
  tags = {
    ExtraTag = "ec2 instance to develop in dev container"
    Owner    = "https://linkedin.com/in/manuel-fernando-gallego-arias/"
    Team     = "Development"
    Project  = "Hotel app"
  }

}
resource "aws_instance" "instance-private-subnet-data-base" {
  ami                         = local.ami-instance-us-east-2
  instance_type               = "t3.micro" # 1 GB Ram
  user_data                   = local.user-data-install-docker-amazon
  key_name                    = "ssh-key-AWS-Instance-us-east-2-user-manuelarias-2025-09-11"
  subnet_id                   = data.terraform_remote_state.network.outputs.private_subnets_ids[0]
  vpc_security_group_ids      = [data.terraform_remote_state.network.outputs.sg_data_base.security_group_id]
  associate_public_ip_address = false
  tags = {
    ExtraTag = "ec2 instance to develop data base"
    Owner    = "https://linkedin.com/in/manuel-fernando-gallego-arias/"
    Team     = "Development"
    Project  = "Hotel app"
  }

}
