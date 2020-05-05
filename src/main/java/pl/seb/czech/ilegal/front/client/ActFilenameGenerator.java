package pl.seb.czech.ilegal.front.client;

import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.Act;

@Service
public class ActFilenameGenerator {
    /*
      files on isap are created in this pattern UyyyyPPPPLj
      where: 
      U - publisher symbol [D - Dz.U. WDU / M - M.P. WMP]
      yyyy - year
      PPPPP - position (if shorter padded left with 0's)
      Lj - additional letters if file is unified text  
     */
    
    private final String unifiedTextSuffix = "Lj";
    private final String extension = ".pdf";

    public String generateUnifiedTxtFilename(Act actToGenerate) {
        String id = actToGenerate.getId();
        char firstLetter = id.charAt(1);
        String year = id.substring(3, 7);
        String position = id.substring(id.length() - 4);
        return firstLetter + year + position + unifiedTextSuffix + extension;
    }

    public String generatePublishedTxtFileName(Act actToGenerate) {
        return generateUnifiedTxtFilename(actToGenerate).replaceAll(unifiedTextSuffix, "");
    }
    
   
    

}
