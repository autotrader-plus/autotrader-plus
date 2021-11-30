package packages.backend_logic;

import java.util.HashMap;

/**
 * A UserFactory that creates User Objects
 */
public class UserFactory {
    /**
     * Create a User object based on user information given in the user hashmap
     * @param user - a hashmap containing user information
     * @return a User object
     */
    public User createUser(HashMap<String, String> user){
        if (user.size() == 6){
            return new User(Integer.parseInt(user.get("credit-score")),
                    Integer.parseInt( user.get("monthlybudget")),
                    Integer.parseInt( user.get("downpayment")), user.get("zip-code"),
                    user.get("name"), user.get("password"));
        } else {
            return new User(Integer.parseInt( user.get("credit-score")),
                    Integer.parseInt( user.get("monthlybudget")),
                    Integer.parseInt( user.get("downpayment")), user.get("zip-code"),
                    user.get("name"), user.get("password"), Integer.parseInt(user.get("monthlyincome")),
                    user.get("employed").equals("employed"),
                    user.get("homeowner").equals("homeowner"),
                    Integer.parseInt(user.get("monthlydebt")));
        }
    }
}
