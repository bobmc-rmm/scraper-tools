tmp.pl

    
#    if( $words[0] =~ /define/ || $line =~ /\;/ ){
	if( $line =~ /\/\*/ ){
	    if( $line =~ /\*\// ){
		$line =~ s/\/\*/ \/\/\/\< /g;
		$line =~ s/\*\///g;
		print ofile "$line\n";
		next;
	    }
	}
 #   }


    for( $c=0; $c < @words; $c++ ){
     print OFILE "$c $words[$c]\n";
     break if( $c > 5 )
    }




