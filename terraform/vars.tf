variable "AWS_REGION" {
  default = "us-east-1"
}
variable "VPC_ID" {
  default = "vpc-74ce420c"
}
variable "SUBNET_ID" {
  default = "subnet-3c414130"
}
variable "PATH_TO_PRIVATE_KEY" {
  default = "mrgkey"
}
variable "PATH_TO_PUBLIC_KEY" {
  default = "mrgkey.pub"
}
variable "AMIS" {
  type = "map"
  default = {
    us-east-1 = "ami-13be557e"
    us-west-2 = "ami-06b94666"
    eu-west-1 = "ami-844e0bf7"
  }
}
variable "INSTANCE_DEVICE_NAME" {
  default = "/dev/xvdh"
}
variable "DOCKER_IMAGE" {
  default = "rajeshgopal/rg-tomcat2:latest"
}
