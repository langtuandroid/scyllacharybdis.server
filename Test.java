import java.util.Iterator;
import java.util.List;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;


public class testy {
	
	private String MY_ACCESS_TOKEN = "";
	FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);

	public testy()
	{
		String query = "SELECT uid, name, picture FROM user WHERE uid IN (SELECT uid2 FROM friend WHERE uid1 = me()) AND is_app_user = 1";
		List<QueryResult> users = facebookClient.executeQuery(query, QueryResult.class);

		Iterator<QueryResult> itr = users.iterator<QueryResult>();

		while( itr.hasNext() ) 
		{
			QueryResult element = itr.next();
			System.out.print(QueryResult.toString);
		}
	}
}


