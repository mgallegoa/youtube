output "instance-public-ip" {
  value       = aws_instance.instance-public-subnet-dev-container.public_ip
  description = "Public IP to access to the app"
}
