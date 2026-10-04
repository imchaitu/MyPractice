import backoff
import requests


@backoff.on_exception(backoff.expo, (requests.exceptions.ConnectTimeout,), max_tries=10)
def test_backoff():
    print("Trying to connect to non-existing")
    requests.get("https://10.0.0.1/", timeout=2)


test_backoff()