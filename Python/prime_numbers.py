import logging
import os
from os import PathLike

mylog = logging.getLogger(__name__)

def check_prime(num, prime_flag=True):
    for i in range(2, int((num/2))+1):
        if num%i == 0:
            prime_flag = False
            break
    return prime_flag
def main(min, max):
    for i in range(min, max+1):
        mylog.debug(f"Checking for {i}...")
        if check_prime(i):
            print(i)


class CreateFileHandler(logging.FileHandler):
    def __init__(self, filename: str | PathLike[str], mode: str = "a", encoding: str | None = None, delay: bool = False, errors: str | None = None) -> None:
        if not os.path.isdir(os.path.dirname(filename)):
            os.makedirs(os.path.dirname(filename))
        super().__init__(filename, mode, encoding, delay, errors)


if __name__ == '__main__':
    print(os.path.basename(__file__))
    # mylog.addHandler(logging.FileHandler("logs/prime_numbers.log"))
    mylog.addHandler(CreateFileHandler(f"logs/{os.path.basename(__file__).split('.')[0]}.log"))
    logging.basicConfig(
        handlers=mylog.handlers+[logging.StreamHandler()],
        level=logging.DEBUG,
        format="%(asctime)s - %(levelname)s - %(name)s - %(message)s",
    )    
    main(10, 50)

