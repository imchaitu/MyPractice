import logging
import os
from os import PathLike

mylog = logging.getLogger(__name__)

class MakeFileHandler(logging.FileHandler):
    def __init__(self, filename: str | PathLike[str], mode: str = "a", encoding: str | None = None, delay: bool = False, errors: str | None = None) -> None:
        if not os.path.isdir(os.path.dirname(filename)):
            try:
                os.makedirs(os.path.dirname(filename))
            except:
                print("Error while creating log path(dir)")
        super().__init__(filename, mode, encoding, delay, errors)


def printFibonachi(last):
    start=1
    second=1
    next=start+second

    fiboList = [start, second, next]

    while(next<=last):
        mylog.info("Generating next number")
        start=second
        second=next
        next=start+second
        fiboList.append(next)
    
    print(fiboList)

if __name__ == '__main__':
    mylog.addHandler(MakeFileHandler(f"logs/{os.path.basename(__file__).split('.')[0]}.log"))
    logging.basicConfig(
        handlers=mylog.handlers + [logging.StreamHandler()],
        level=logging.INFO,
        format='%(asctime)s - %(levelname)s - %(name)s - %(message)s'
    )

    printFibonachi(200)
