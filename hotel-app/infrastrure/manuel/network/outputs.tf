output "vpc_id" {
  value = module.vpc.vpc_id
}

output "private_subnets_ids" {
  value       = module.vpc.private_subnets
  description = "List of private subnets ids"
}

output "public_subnets_ids" {
  value       = module.vpc.public_subnets
  description = "List of public subnets ids"
}

output "sg_dev_container" {
  value       = module.sg_hotel_app_https_http_ssh
  description = "Security group id for dev container"
}

output "sg_data_base" {
  value       = module.sg_hotel_app_ssh
  description = "Security group id for data base"
}
