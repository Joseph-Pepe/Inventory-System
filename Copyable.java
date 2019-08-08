//Make the clone method public instead of protected.
public interface Copyable extends Cloneable
{
	public Object clone();
}
