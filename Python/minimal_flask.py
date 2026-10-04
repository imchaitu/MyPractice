from flask import Flask, url_for
from markupsafe import escape

app = Flask(__name__)

@app.route('/')
def welcome():
    return f"""{escape('<script>alert("bad")</script>')}"""

def testing():
    return "This is testing function"


if __name__ == '__main__':
    app.run()