resource "aws_instance" "example" {
  ami           = "${lookup(var.AMIS, var.AWS_REGION)}"
  instance_type = "t2.micro"

  # the VPC subnet
  subnet_id = "${var.SUBNET_ID}"

  # the security group
  vpc_security_group_ids = ["${aws_security_group.allow-ssh.id}"]

  # the public SSH key
  key_name = "rg-newkey"

  # user data
  user_data = "${data.template_cloudinit_config.cloudinit-example.rendered}"

  tags {
    Name = "SpringBootApp"
  }

}
