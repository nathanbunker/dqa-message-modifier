package org.immregistries.dqa.message_modifier;

import org.immregistries.dqa.message_modifier.ModifierService;
import org.immregistries.dqa.message_modifier.ModifyRequest;

import junit.framework.TestCase;

public class ModifyRequestTest extends TestCase {
  public void testInitial() {
    // Assignments
    {
      String modificationScript = "PID-5=\"\";\n";
      
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.1=\"\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5[2].1=\"\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[2]-5.1=\"\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
      		  				     "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
  				  			     "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.1=\"Watson\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.2 = $PID-5.1";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.2 = $PID-5";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    //To the stars
    
    {
        String modificationScript = "PID-5.*=\"Watson\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[*]-5.*=\"Watson\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
	  			     			 "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5[*].1=\"Watson\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L~Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-*=\"Watson\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|Watson|Watson|Watson^^^AIRA-TEST^MR|Watson|Watson^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Watson^Arden|Watson|Watson|Watson|Watson|Watson^^Cadmus^MI^49221^USA^P|Watson|Watson^PRN^PH^^^517^3004208|Watson";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-*[*]=\"Watson\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|Watson|Watson|Watson^^^AIRA-TEST^MR|Watson|Watson^Jeramiah^Z^IV^^^L~Watson^Jeramiah^Z^IV^^^L|Watson^Arden|Watson|Watson|Watson|Watson|Watson^^Cadmus^MI^49221^USA^P|Watson|Watson^PRN^PH^^^517^3004208|Watson";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-*[*].*=\"Watson\"";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|Watson|Watson|Watson^Watson^Watson^Watson^Watson|Watson|Watson^Watson^Watson^Watson^Watson^Watson^Watson~Watson^Watson^Watson^Watson^Watson^Watson^Watson|Watson^Watson|Watson|Watson|Watson|Watson|Watson^Watson^Watson^Watson^Watson^Watson^Watson|Watson|Watson^Watson^Watson^Watson^Watson^Watson^Watson|Watson";
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
        String modificationScript = "for $RXA-1 call map(\"MMR\" => \"03\", \"Default\" => \"Unknown\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // Truncate
    {
        String modificationScript = "for $RXA-9.2 call trunc(\"max\" => 5);";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Admin^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-1 call trunc(\"max\" => 5);";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // Clearing fields
    
    {
        String modificationScript = "for $PID call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID||||||||||||||";
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
    
    //Cleaning fields (not done yet)
    {
        String modificationScript = "call clean();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-5 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR|||Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);

    }

    
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
    // empty character at the end of messagefinal in modifyRequest fails the test
    //String test = modifyRequest.getMessageFinal().substring(0, modifyRequest.getMessageFinal().length()-1);
    assertEquals(messageFinal, modifyRequest.getMessageFinal()); 
  }
}
