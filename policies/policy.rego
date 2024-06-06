package authz

default allow = false

allow {
	input.user.role == "admin"
}

allow {
    input.user.path == "/general"
}

allow {
	input.user.role == "user"
	regex.match("^(/user)(/.*)?$", input.user.path)
}

allow {
	input.user.role == "accountant"
	regex.match("^(/accountant)(/.*)?$", input.user.path)
}
