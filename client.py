import requests
import random

URL = f"http://localhost:8080/measurements/add"
attempts = int(input("Enter the count of measurements --> "))
tempAttempts = attempts
while attempts > 0:

    VALUE = round(random.uniform(-100, 100), 1)
    IS_RAINING = random.choice(["true", "false"])
    SENSOR_NAME = "sensor" + str(random.randint(1, 10))

    # Generate random parameters
    data = {
        "value": VALUE,
        "raining": IS_RAINING,
        "sensor": {
            "name": SENSOR_NAME
        }
    }

    r = requests.post(url=URL, json=data)

    if r.status_code != 200:
        print("Error. Check input parameters.")

    attempts -= 1

print(str(tempAttempts) + " measurement(s) added.")
