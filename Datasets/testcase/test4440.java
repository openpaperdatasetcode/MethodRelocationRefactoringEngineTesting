package test;
interface SourceInterface {/**
Overloaded method to process TargetInterface and return its inner class instance
@param target TargetInterface implementation
@return TargetInterface.Inner instance
*/
synchronized <T extends TargetInterface> TargetInterface.Inner getTargetInner(T target);
/**
Overloaded method with String parameter
@param str Input string
@return TargetInterface.Inner instance
*/
synchronized TargetInterface.Inner getTargetInner(String str);
default TargetInterface.Inner defaultMethod() {TargetInterface target = new TargetInterface() {@Overridepublic void targetMethod() {}};TargetInterface.Inner inner = TargetInterface.Inner::new;int fieldVal = inner.innerField;return getTargetInner(target);}}
default interface TargetInterface {void targetMethod();
class Inner {int innerField = 1;}}