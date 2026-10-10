#!/bin/bash
set -eu

oa=
ob=
oc=

function test(){
    echo "test"
}

function main(){


    while getopts a:b:c opt
    do
        case $opt in
        a) oa="$OPTARG";;
        b) ob="$OPTARG";;
        c) oc="$OPTARG";;
        ?) printf "Usage: %s -a <val> -b <val> -c <val>\n" $0
           exit 2;;
        esac
    done
    shift $(($OPTIND - 1))

    test;
}

main $@