package authz

default allow = false

allow {
    input.path == "/general"
}

allow {
    input.role == "admin"
}

allow {
    input.role == "user"
    input.path == "/user"
}

allow {
    input.role == "user"
    input.path == "/general"
}

allow {
    input.role == "accountant"
    input.path == "/accountant"
}

allow {
    input.role == "accountant"
    input.path == "/general"
}
