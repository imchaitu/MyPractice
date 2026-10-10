from functools import wraps

def decorator_function(some_arg):
    def _decorator_function(func):
        @wraps(func)
        def wrapper():
            print("in wrapper")
            print("This is decorator arg " + some_arg)
            return func()
        return wrapper
    return _decorator_function

@decorator_function(some_arg='test_arg')
def test_decorator():
    "This is the doc for test_decorator."
    print('This is main function')
    

# test_decorator()
print(test_decorator.__name__)
print(test_decorator.__doc__)