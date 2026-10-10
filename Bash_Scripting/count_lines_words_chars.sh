#!/bin/bash
set -eu

lines="false"
words="false"
chars="false"
all="false"
file_name=


function countFile(){
    myArgs=
    header=
    if [[ $lines == "false" && $words == "false" && $chars == "false" ]]; then
        all="true"
    fi
    if [[ $lines == "true" || $all == "true" ]]; then
        myArgs="$myArgs -l"
        header="${header}Lines "
    fi
    if [[ $words == "true" || $all == "true" ]]; then
        myArgs="$myArgs -w"
        header="${header}Words "
    fi
    if [[ $chars == "true" || $all == "true" ]]; then
        myArgs="$myArgs -m"
        header="${header}Chars"
    fi

    echo "$header"
    wc $myArgs < $file_name
}

function main(){

    while getopts lwcf: opt
    do
        case $opt in
        l) lines="true";;
        w) words="true";;
        c) chars="true";;
        f) file_name="$OPTARG";;
        ?) printf "Usage: %s [-l] [-w] [-c] [-a] -f <file_name>\n" $0
           exit -2;;
        esac
    done
    shift $(($OPTIND -1))

    countFile;
}

main $@