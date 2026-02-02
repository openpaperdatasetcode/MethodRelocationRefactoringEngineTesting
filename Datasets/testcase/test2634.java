package test.same;
strictfp class SourceClass extends ParentClass {static class StaticNestedOne {}static class StaticNestedTwo {}
class MemberInner {record InnerRec(TargetClass target) {@Overridestrictfp int hashCode() {Object var = target.anonField;synchronized (target) {var = target.anonField;target.anonField = 10;}return (int) var;}}}}
class ParentClass {public TargetClass callMethod() {try {return this.createTarget();} catch (Exception e) {throw new RuntimeException(e);}}
protected TargetClass createTarget() {return new TargetClass();}}
class TargetClass {Object anonField;
Runnable anon = new Runnable() {public void run() {anonField = 5;}};}