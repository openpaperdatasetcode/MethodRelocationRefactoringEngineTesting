package same.pkg;
interface SourceInterface {int targetField = 0; // Field of target interface
static class StaticNested1 {}static class StaticNested2 {}
default int method() {variableCall();
expressionStmt:{int exprResult = TargetInterface.targetValue + 5;}
new ParentClass().instanceMethod();
int i = 0;while (i < 3) {if (i == 1) {break;}i++;}
return 42;}
private void variableCall() {int localVar = TargetInterface.targetValue;}}
protected interface TargetInterface {int targetValue = 10; // Field contained in source}
class ParentClass {public TargetInterface instanceMethod() {return new TargetInterface() {};}}