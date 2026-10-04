#!/bin/bash

# Defining a vairable
varWithotuVal=           # Initialization without value
varWithVal='varValue'   # Initialization with value
# No space before or after '=' sign. If space is give, throughs an error as bash interprits it as command with parameters.
# There are no way to specify the data type. Just variable name and value.
# Depending on the context and syntax used on the variables, sometimes you will be able to see the difference.
# For example, if you use the variable in 'echo' or 'string concatination', even the value is number, behaves as string.
# But, if you used the variable in 'eval', it number behaves as number and calculation happens.

# Printing variable value
echo $varWithoutVal
echo $varWithVal

# If the variable is not defined, it will just be replaced with blank string, but error will not be thrown
echo $undefinedVar

# String concatination
stringConcat=$varWithVal"+ConcatString"
echo $stringConcat
stringConcat=$varWithVal+'ConcatString'
echo $stringConcat
stringConcat="$varWithVal+ConcatString"
echo $stringConcat
stringConcat=$varWithVal
stringConcat+="+ConcatString"
echo $stringConcat

# Variable evaluation in string. Format string.
# A variable is expanded if it is inside doubel quotes (").
# If the variable is in single quotes ('), it won't be evaluated, but considered as literal and the '$' symbol is printed as it is.
varEval="This is the var value: $varWithVal"
echo $varEval
varEval='Here the var is not evaluated, but considered as literal: $varWithVal'
echo $varEval
# When you use the format string (placing the variable in string direcly like above), there can be some special cases.
# You might want to add some carecters concatinating to the string. In such case you have to use '{}'
varEvalWithConcat="This is concatinating var in-line: ${varWithVal}AdditionalChars"
echo $varEvalWithConcat
# If you don't use '{}', the total string 'varWithValAdditionalChars' is considered as variable and as this is not defines, you will see blank output. 
echo "This is wrong in-line concatination: $varWithValAdditionalChars"

# Multi-line string variable
multiLineStr="This is line one.
This is line two.
This is line three."
# However, the above is evaluated as multi-line only when the varibale is placed in double quote (")
# If the varibale is not enclosed in ("), the full string is considered as single line.
echo $multiLineStr
echo "$multiLineStr"

# There is another way for this: Using '\n' in string and using 'echo -e'
multiLineStr2="This is line one.\nThis is line two.\nThis is line three."
echo -e $multiLineStr2

# You can feed multi-line string directly to a command without storing it in a variable using '<<' and marker ('EOF')
cat <<EOF
This is line 1.
This is line 2.
This is line 3.
EOF
# Here 'EOF' has no special meaning. It is just a marker. You can use any charecters there (EOL, E, X, ...)
# But you have to used the same marker at both places.


# We have worked with only strings in this exercise as all the variable are considered as strings when you define and print them.
# Only in some cases, other data types come into play dynamically.



