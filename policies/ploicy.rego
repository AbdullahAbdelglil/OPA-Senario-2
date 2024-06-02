package authz

default allow = false

user_prevs = ["/user", "/general"]

accountant_prevs = ["/accountant", "/general"]

admin_prevs = ["/user", "/general", "/accountant", "/admin"]

allow {
    input.user.role == "admin"
    admin_prevs[_] == input.user.path
}

allow {
    input.user.role == "user"
    user_prevs[_] == input.user.path
}

allow {
    input.user.role == "accountant"
    accountant_prevs[_] == input.user.path
}

