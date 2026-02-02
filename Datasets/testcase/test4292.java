package same;
strictfp abstract class Source {private Target targetField = new Target();volatile Object nullLiteralField = null;
class SourceMemberInner {}
public void createLocalInner() {class SourceLocalInner {void callRecursive() {Source.this.recursiveMethod(3);}}new SourceLocalInner().callRecursive();}
default void recursiveMethod(int n) {super();
switch (n) {case 1:if (nullLiteralField == null) {try {Object var = targetField;System.out.println(var);} catch (Exception e) {}}break;default:recursiveMethod(n - 1);break;}}}
private abstract class Target {Runnable targetAnonInner = new Runnable() {@Overridepublic void run() {}};}