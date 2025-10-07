variable "secret_key" {
  type        = string
  description = "Secret key for the hotel app"
  sensitive   = true
}

variable "access_key" {
  type        = string
  description = "Access key for the hotel app"
  sensitive   = true
}
