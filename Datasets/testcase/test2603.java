package test.same;
strictfp class SourceClass {class MemberInner {}
private Object instanceMethod(TargetClass target) {TargetClass.MemberInner.InnerRec rec = target.new MemberInner().new InnerRec();Object var = rec.field;int staticVal = TargetClass.staticField;
for (int i = 0; i < 2; i++) {if (rec.field.equals(i)) {break;}}
int j = 0;do {j++;} while (j < staticVal);
return var;}
void createLocal() {class LocalInner {}}}
protected class TargetClass {static int staticField = 3;
class MemberInner {record InnerRec() {Object field;}}}