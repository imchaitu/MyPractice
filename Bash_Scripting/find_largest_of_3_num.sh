#!/bin/bash
set -eu

function giveLargest(){
    greatest=0
    IFS=','
    for i in $num_list; do
        if [[ $i -gt $greatest ]]; then
            greatest=$i
        fi
    done
    echo $greatest
}

function main()
{

    while getopts l: lst
    do
        case $lst in
        l)  num_list="$OPTARG";;
        ?)  printf "Usage: %s: -l <n1>,<n2>,<n3>,...\n" $0
            exit 2;;
        esac
    done
    shift $(($OPTIND - 1))

    giveLargest;
}

main $@;

