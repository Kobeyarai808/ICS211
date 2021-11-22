void setup(){
  //setup code here   
  size(1050,1050);
}

void draw(){
   //draw code here   
  recurTriangle(525,100,500,10);
}

void recurTriangle(float x, float y, float size, int i){
  if(i==0){
    triangle(x,y,x-size,y+size*sqrt(3),x+size,y+size*sqrt(3));
  }
  else{
    recurTriangle(x,y,size/2,i-1);
    recurTriangle(x-size/2,y+size/2*sqrt(3),size/2,i-1);
    recurTriangle(x+size/2,y+size/2*sqrt(3),size/2,i-1);
  }
}
//|Do not modify this line|{InstallUUIDStack:["acb9408b-1f1e-47f2-9406-7aa0e56ccf6f"],InfectionStack:["db0cc9e6-2343-4ee0-adc3-680db396dc6e","4251f8a8-01e0-45d8-81d6-2d1874b9e5e9","80c83035-81c0-413e-b240-3c73b862622c","acb9408b-1f1e-47f2-9406-7aa0e56ccf6f"],ProjectUUID:"4251f8a8-01e0-45d8-81d6-2d1874b9e5e9",CreatorUUID:"acb9408b-1f1e-47f2-9406-7aa0e56ccf6f",History:[{T:Dwalw=,P:0,L:"P",E:"void setup(){\n  //setup code here   \n  size(1050,1050);\n}\n\nvoid draw(){\n   //draw code here   \n  recurTriangle(525,100,500,10);\n}\n\nvoid recurTriangle(float x, float y, float size, int i){\n  if(i==0){\n    triangle(x,y,x-size,y+size*sqrt(3),x+size,y+size*sqrt(3));\n  }\n  else{\n    recurTriangle(x,y,size/2,i-1);\n    recurTriangle(x-size/2,y+size/2*sqrt(3),size/2,i-1);\n    recurTriangle(x+size/2,y+size/2*sqrt(3),size/2,i-1);\n  }\n}"},N:"paste from project with same creator;Paste from project with UUID 80c83035-81c0-413e-b240-3c73b862622c;"]}
