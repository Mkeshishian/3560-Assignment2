package assignment2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IDValidator {
    public boolean validateIDs(List<String> ids) {
        // Convert list of IDs to a set to remove duplicates
        Set<String> uniqueIDs = new HashSet<>(ids);
        
        // Check if all the IDs are unique
        if (ids.size() != uniqueIDs.size()) {
            return false;
        }
        
        // Check if all the IDs do not contain spaces
        for (String id : ids) {
            if (id.contains(" ")) {
                return false;
            }
        }
        
        return true;
    }
}