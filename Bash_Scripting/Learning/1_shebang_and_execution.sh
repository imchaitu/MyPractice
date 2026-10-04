#!/bin/bash

# The above line is called "Sheebang".
# This will be used to determine which interpreter is to be used for the script execution.
# This is optional, but if specified, it should be the first line of the script, else it will be ineffective.
# The special syntax '#!' is used. Generally, comments in bash start with #. However '#!' is a special syntax defined for sheebang.
# Need to specify the absolute path of the interpreter.
# Will be effective when you run the script as " ./<script>.sh ". In other ways of execution, sheebang is neglected.

# #########
# Comments:
# #########
# The line starting with '#' are considered as comments in bash scripting.


# ##################
# Type of Execution:
# ##################
# There are 3 ways to execute the script on the command line.

# 1. ./<script>.sh
# In this case:
# The file should have executable (x) permissions.
# The interpreter is determined by the sheebang. If there is no sheebang, then the current shell is taken as interpreter.
# Forks a subprocess, does the execution and executes.
# The variables or functions defined in the script are only accissible from the script (As another shell is opened as a subprocess for this script execution and that shell is killed after the script execution)

# 2. <shell> <script>.sh   Eg: sh <script>.sh
# No need of executable (x) permissions.
# The interpreter is specified on the command line already 'sh'. The sheebang is neglected.
# Forks a subprocess, sh in this case, and then runs the script in that subprocess.
# Again the variables or functions defined in the script are only accissble from the script during the run time because of the same reason.

# 3. . <script>.sh
# No need of executable (x) permissions.
# The current shell is the interpreter.
# Doesn't fork a subprocess, but runs in the current shell itself.
# The variables or functions that are defined in the script are available even after the script execution is done.