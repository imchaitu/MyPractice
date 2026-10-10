import logging, os
from os import PathLike
import click

class MyFileHandler(logging.FileHandler):
    def __init__(self, filename: str | PathLike[str], mode: str = "a", encoding: str | None = None, delay: bool = False, errors: str | None = None) -> None:
        if not os.path.isdir(os.path.dirname(filename)):
            os.makedirs(os.path.dirname(filename))
        super().__init__(filename, mode, encoding, delay, errors)
    
mylog = logging.getLogger(__name__)


def add(a, b):
    return a+b

def substract(a, b):
    return a-b

def multiply(a, b):
    return a*b

def divide(a, b):
    return a/b


@click.command()
@click.option('-f', '--function', type=click.Choice(['a', 's', 'm', 'd']), required=False, help="The calculator function you want to execute.")
@click.option('-a', '--number1', type=int, required=True, help="The first integer number.")
@click.option('-b', '--number2', type=int, required=True, help="The second integer number")
def main(function, number1, number2):

    funcs = {'a': add, 'b': substract, 'm': multiply, 'd': divide}

    if not function:
        function='a'

    answer = funcs[function](number1, number2)
    print(answer)

if __name__ == '__main__':
    mylog.addHandler(MyFileHandler(f"logs/{os.path.basename(__file__).split('.')}.log"))
    logging.basicConfig(
        handlers=mylog.handlers + [logging.StreamHandler()],
        level=logging.INFO,
        format='%(asctime)s - %(levelname)s - %(name)s - %(message)s'
    )

    main()

    