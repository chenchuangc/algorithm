class Employee:
    name=""
    def __init__(self,lname):
        self.name=lname
    def getName(self):
        return self.name

e=Employee("slef_name_aaa")
Employee.name="class_name"
print e.getName()
print Employee.name
