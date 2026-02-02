package same.pkg;
private class Source {class Inner1 {}class Inner2 {}
/**
Javadoc for accessor method
@param targetParam parameter of Target type
@return Target instance
*/
public final Target getTarget(Target targetParam) {
Target varCall = targetParam.clone();
return varCall;
}
}
final class Target implements Cloneable {public void someMethod() {class LocalInner {}}
@Overridepublic Target clone() {try {return (Target) super.clone();} catch (CloneNotSupportedException e) {throw new AssertionError();}}}