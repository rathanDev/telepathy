
# -------------------------------------------------------------------------- #

Http Request

Post
localhost:8080/plan

RequestBody: json
{
    "availablePlans": [
        {
            "name": "PLAN1",
            "amount": 100,
            "features": ["voice", "email"]
        },
        {
            "name": "PLAN2",
            "amount": 150,
            "features": ["email", "database", "admin"]
        },
        {
            "name": "PLAN3",
            "amount": 125,
            "features": ["voice", "admin"]
        },
        {
            "name": "PLAN4",
            "amount": 135,
            "features": ["database", "admin"]
        }
    ],
    "requirements": ["email", "voice", "admin"]
}

Response:
{
    "planNames": [
        "PLAN1",
        "PLAN3"
    ],
    "amount": 225
}