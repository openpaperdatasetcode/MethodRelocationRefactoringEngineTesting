package same.pkg;
class Source {// Two anonymous inner classesRunnable anon1 = new Runnable() {@Overridepublic void run() {}};
Runnable anon2 = new Runnable() {@Overridepublic void run() {}};
// Member inner recordrecord SourceInnerRec() {// Method in source_inner_recprivate int normalMethod(Target targetParam, int keywords) {// Method types parameter is:keywords (using 'keywords' parameter)if (keywords < 0) {return -1;}
// If statementif (targetParam.field == 5) {System.out.println("Field is 5");}
// Labeled statementloop: {for (int i = 0; i < 3; i++) {if (i == 1) break loop;}}
// Type declaration statementTarget targetVar;
// ArrayCreation with numbers=1public int[] arr = new int[1];
// Variable calltargetVar = targetParam;int varCall = targetVar.getValue();
// Depends on static fieldint staticVal = Target.staticField;
return varCall + staticVal;}}}
class Target {int field;static int staticField = 3;
int getValue() {return field;}}