package test.same;
class SourceClass {class MemberInnerClass {record InnerRec() {final void normalMethod(TargetClass target) {super();Object var = target.field;int val = 1;
switch (val) {case 1:break;default:break;}
int i = 0;while (i < 5) {i++;}
if (val < 0) {throw new RuntimeException(new InnerClass().privateMethod());}}
private void privateMethod() {}}}
Runnable anon = new Runnable() {public void run() {}};}
class TargetClass {Object field;}