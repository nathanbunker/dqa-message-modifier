package org.immregistries.dqa.message_modifier;

import org.immregistries.dqa.message_modifier.ModifierService;
import org.immregistries.dqa.message_modifier.ModifyRequest;

import junit.framework.TestCase;

public class ModifyRequestTest extends TestCase {
	private int count=0;
  public void testInitial() {
    // use context
    {
      String modificationScript = "use context::Immunization;";
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String messageFinal =    messageOriginal;
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    // Assignments
    {
      String modificationScript = "PID-5=\"\";";
      
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5=\"\";\rPID-6=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
      }
    {
        String modificationScript = "PID-5.1=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5[2].1=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[2]-5.1=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
      		  				     "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
  				  			     "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.1=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.2 = $PID-5.1;";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.2 = $PID-5;";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    //To the stars
    
    {
        String modificationScript = "PID-5.*=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        //runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[*]-5.*=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
	  			     			 "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		//runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5[*].1=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L~Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        //runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[*]-5=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n"+
        						 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal = "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n"+
        					  "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        //String messageFinal =    "PID|Watson|Watson|Watson^^^AIRA-TEST^MR|Watson|Watson^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Watson^Arden|Watson|Watson|Watson|Watson|Watson^^Cadmus^MI^49221^USA^P|Watson|Watson^PRN^PH^^^517^3004208|Watson";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    // Mapping
    {
        String modificationScript = "for $RXA-5.2 call map(\"PCV 13\" => \"03\", \"Default\" => \"Unknown\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^03^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-4 call map(\"MMR\" => \"03\", \"Default\" => \"Unknown\");";
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104|Unknown|133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^ABC 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // Truncate
    {
        String modificationScript = "for $RXA-9.2 call trunc(\"max\" => \"5\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Admin^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-1 call trunc(\"Max\" => \"5\", \"cut\"=>\"left\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-5.2 call trunc(\"MAX\" => \"1\", \"cut\"=>\"right\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^3^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "for $RXA-5.2 call trunc(\"MAX\" => \"2\", \"cut\" => \"right\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    // Clearing fields
    
    {
        String modificationScript = "for $PID call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-5 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR|||Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-5[2] call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
        
    }
    {
        String modificationScript = "for $PID-3[4] call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
        
    }
    
    //Cleaning fields
    {
        String modificationScript = "call clean();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "call clean(\"no last slash\" => \"true\");";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208\n" +
    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "call clean(\"no last slash\" => \"false\");";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // Questions about fixes : call fixAmpersand();  call fixEscape(); what does they do?
    // For fixAmpersand the any & becomes \T\ (except for in heading)
    // For fixEscape() any single \ becomes \E\ (except for in heading)
    {
        String modificationScript = "call fixAmpersand();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus&Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\T\\Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "call fixEscape();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\E\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    
    
    // Adding and removing segments
    
    {
        String modificationScript = "for $PID call insertAfter(\"Segment Id\" => \"PD1\");";
        // Do we put the right number of pipes?
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $NK1 call insertBefore(\"Segment Id\" => \"PID\");";
        // Do we put the right number of pipes?
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "for $RXA[2] call insertAfter(\"Segment Id\" => \"RXR\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "RXR|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }    
    {
        String modificationScript = "call insertLast(\"Segment Id\" => \"PV1\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "PV1|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }    
    {
        String modificationScript = "call insertFirst(\"Segment Id\" => \"BHS\", \"copy values from MSH\" => \"true\")";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    
    {
        String modificationScript = "for $PID call insertAfter(\"Segment Id\" => \"PD1\", \"if missing\" => \"true\");";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID call insertAfter(\"Segment Id\" => \"PD1\", \"if missing\" => \"true\");";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // If then else
    
    {
        String modificationScript = "for $PID if (PID-7 == \"20160626\") then call clear();";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID||||||||||||||\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID if (PID-7 == \"20160625\") then call clear();";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-7 if (PID-7 == \"20160626\") then call clear();";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-7 if (PID-7 == \"20160625\") then call clear();";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "for $PID-7 if (PID-7 == \"20160625\") then call clear();";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-5.2 if (PID-5.2 == \"Jeramiah\") then call trunc(\"max\" => 2);";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Je^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "if (RXA-5 == \"94\" and RXA-6 != \"06\") then RXA-4.1 = \"Hello\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
	        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204|Hello|94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    
    
    
     
 
    // if (call ageGreaterThan("18")) then RXA-4.1 = "Hello" : Where can I get the age of concerned patient?

    {
        String modificationScript = "for $PID-5 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
  }	  

  private void runTest(String messageOriginal, String modificationScript, String messageFinal) {
	    ModifierService modifierService = new ModifierService();
	    ModifyRequest modifyRequest = new ModifyRequest();
	    modifyRequest.setMessageOriginal(messageOriginal);
	    modifyRequest.setModificationScript(modificationScript);
	    modifierService.modify(modifyRequest);
	    // empty character at the end of messageFinal in modifyRequest fails the test
	    String FinalMessage = modifyRequest.getMessageFinal().trim();
	    // \n are replaced with \r during modifications
	    FinalMessage = FinalMessage.replace("\r", "\n");
	    assertEquals(messageFinal, FinalMessage);
	    count++;
	    System.out.print("Test "+count+" sucessful");
	    //assertEquals(messageFinal,modifyRequest.getMessageFinal()); 
	  }
}
