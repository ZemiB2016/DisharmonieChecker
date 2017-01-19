import commands
import glob
import numpy
import os
import re

FEW = 5

def take_in_output(file_name):
    output = commands.getoutput("test_program " + file_name)
    r = re.compile("([a-zA-Z]+) : ([0-9]+)")
    data_list = r.findall(output)
    d = {}
    for type, val in data_list:
        d[type] = val
    return d

def check_directory(path):
    data_list = {}
    files = os.listdir(path)
    for file in files:
        if os.path.isdir(path + "/" + file):
            data_list.update(check_directory(path + "/" + file))
    files = glob.glob(path + "/*.txt")
    for file in files:
        data_list[file] = take_in_output(file)
    return data_list

def calc_thresholds(data_list):
    value_list = {}
    thresholds = {}
    for file_data in data_list.values():
        for key, val in file_data.items():
            if not key in value_list.keys():
                value_list[key] = []
            value_list[key].append(int(val))
    for type, values in value_list.items():
        array = numpy.array(values)
        ave = numpy.average(array)
        std = numpy.std(array)
        d = {
            "Low": ave - std,
            "High": ave + std,
            "VeryHigh": (ave + std) * 1.5,
        }
        thresholds[type] = d
    return thresholds

def check_godclass(data_list, thresholds):
    godclass_list = []
    for file, file_data in data_list.items():
        if FEW < file_data["ATFD"] and file_data["WMC"] > thresholds["WMC"]["VeryHigh"]\
            and file_data["TCC"] < 1/3:
            godclass_list.append(file)
    return godclass_list

if __name__ == '__main__':
    project_data_list = {}
    projects = os.listdir("./")
    for project in projects:
        if os.path.isdir("./" + project):
            data_list = check_directory("./" + project)
            thresholds = calc_thresholds(data_list)
            project_data_list[project] = check_godclass(data_list, thresholds)
    print(project_data_list)
