package backend;

public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}
