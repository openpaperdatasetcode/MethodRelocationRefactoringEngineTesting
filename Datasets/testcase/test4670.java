package test;
interface SourceInterface {default void createInner() {class LocalInner {strictfp Object process(TargetInterface.Inner targetInner, int count) {/**
Processes target inner with recursion and loops
@param targetInner Target inner interface instance
@param count Recursion count
@return Processed object
*/
if (count <= 0) return null;
synchronized (this) {do {switch (count) {case 3:System.out.println("Count 3");break;case 2:System.out.println("Count 2");break;case 1:System.out.println("Count 1");break;}count--;} while (count > 0);}
new Runnable() {@Overridepublic void run() {targetInner.execute();}}.run();
return process(targetInner, count - 1);}}
TargetInterface target = new TargetInterface() {@Overridepublic Inner createInner() {return () -> System.out.println("Target inner executed");}};LocalInner local = new LocalInner();local.process(target.createInner(), 3);}}
strictfp interface TargetInterface {interface Inner {void execute();}Inner createInner();}
