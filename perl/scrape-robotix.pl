#!/usr/bin/perl
###############################################################
#
print "==== scrape-robotix.pl ====\n";
print "2017-may-16, 2573098.inc\n\n";
#my $file = $ARGV[0];

## global variables
$file = "Robotix.html";
@array, @words;
$line="";
$name1="", $model1="", $price1="";

## use STATE to say what parameters have been extracted
$STATE_INIT = 0;
$STATE_NAME1 = 1;
$STATE_MODEL1 = 2;
$STATE_PRICE1 = 3;
$state = $STATE_INIT;

if( $file eq "" ) {
    print "\n",
    "Scans an HTML page. Prints PRODUCT,MODEL,PRICE\n",
    "usage: perl the-page.html\n";
    exit(1);
}

print "in file is:- $file\n";
open(IFILE, $file) || die("Can't open input file $file\n");
@array = <IFILE>;
close (IFILE);

$file = "Robotix-scraped.txt";
open(OFILE, ">$file") || die("Can't open output file $file\n");
print "out file is:- $file\n";

sub select_name1;
sub select_model1;
sub select_price1;

foreach $line (@array) {
    chop($line);			    
    $line =~ s/\t/ /g;
    @words = split( / +/, $line );
    $line = join(" ",@words);
    if( $state == $STATE_INIT ){
!	if( &select_name1 > 0 ){
	    $state = $STATE_NAME1;
	    next;
	}
    }

    if( $state == $STATE_NAME1 ){
	if( &select_model1 > 0 ){
	    $state = $STATE_MODEL1;
	    next;
	}
    }

    if( $state == $STATE_MODEL1 ){
	if( &select_price1 > 0 ){
	    $state = $STATE_PRICE1;
	    next;
	}
    }

    if( $state == $STATE_PRICE1 ){
	print("Name :", $name1,"\n");
	print("Model:", $model1,"\n");
	$price1 = $words[1];
	print("Price:", $price1,"\n");
	$state = $STATE_IDLE;
    }

}
close (OFILE);


## ------------------------------------------------------
sub select_name1 {
    
    if( $line =~ /class\=\"name\"/ ){
	@words = split( /\>|\</, $line );
	$name1 = substr($words[4],0,55);
	return 1;
    }
    return 0;
}

## ------------------------------------------------------
sub select_model1 {
    if( $line =~ /class\=\"model\"/ ){
	@words = split( /\>|\</, $line );
	$model1 = $words[2];
	return 1;
    }
    return 0;
}


## ------------------------------------------------------
## quirk: the price spans 2 HTML lines
sub select_price1 {
    if( $line =~ /class\=\"price\"/ ){
	return 1;
    }
    return 0;
}

## ------------------------------------------------------
sub body {
    print ("$spacer\n");
}


### end mpage.pl ###
