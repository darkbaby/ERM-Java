import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
 

 
public class Sha3 {
	 public static void main(String[] arg){
		 String input = "p@ssw0rd";
		 String input2 = "THAI";
		    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		    byte[] digest = digestSHA3.digest(input.getBytes());
		    String code1 = Hex.toHexString(digest) + 1 ;
		    byte[] digest2 = digestSHA3.digest(code1.getBytes());
		    String code2 = Hex.toHexString(digest2);
		    System.out.println("1 SHA3-512 = " + code1);
		    System.out.println("2 SHA3-512 = " + code2);
 	 }
}
