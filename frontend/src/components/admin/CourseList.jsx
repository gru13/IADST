// CourseList.jsx
// Table of courses and memberships
import React, { useEffect, useState } from "react";
import { FaSearch } from "react-icons/fa";
import { IoAddCircleOutline } from "react-icons/io5";
import { MdOutlineDelete, MdOutlineEdit, MdOutlineSave } from "react-icons/md";
import api from '../../api/axios';


function CourseCard({ element, allStudents, onUpdateCourse }) {
  const [item, setItem] = useState({ ...element });
  const [edit, setEdit] = useState(false);
  const [teachersList, setTeacherList] = useState([]);
  // Use student objects for this course
  const [studentsList, setStudentsList] = useState([]);

  useEffect(() => {
    const getTeacherList = async () => {
      try {
        const response = await api.get('/admin/teachers/all/list/name'); // Remove duplicate 'api/' if present
        setTeacherList(response.data);
      } catch (error) {
        alert(`Error fetching teachers: ${error.message}`);
      }
    };
    getTeacherList();
    // Set studentsList from allStudents using item.students (array of IDs)
    if (item.students && Array.isArray(item.students)) {
      setStudentsList(
        allStudents.filter(s => item.students.includes(s.id))
      );
    } else {
      setStudentsList([]);
    }
  }, [item.id, item.students, allStudents]);

  // Add/remove student handlers (to be used in UI)
  const handleAddStudent = (studentId) => {
    if (!item.students.includes(studentId)) {
      const updated = { ...item, students: [...item.students, studentId] };
      setItem(updated);
      setStudentsList(allStudents.filter(s => updated.students.includes(s.id)));
      if (onUpdateCourse) onUpdateCourse(updated);
    }
  };
  const handleRemoveStudent = (studentId) => {
    const updated = { ...item, students: item.students.filter(id => id !== studentId) };
    setItem(updated);
    setStudentsList(allStudents.filter(s => updated.students.includes(s.id)));
    if (onUpdateCourse) onUpdateCourse(updated);
  };

  const handleChanges = (e) => {
    setItem((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleDelete = () => { };


  return (
    <div className="p-6 rounded-2xl border-2 border-gray-300 shadow-md m-4 flex flex-col w-full max-w-lg ">
      <div className="flex flex-col gap-2 mb-4">
        <input
          type="text"
          className="px-3 py-2 text-2xl rounded-lg border border-gray-200 focus:border-emerald-500 focus:outline-none "
          placeholder="Course Name"
          value={item.courseName}
          readOnly={!edit}
          onChange={handleChanges}
        />
        <select
          name="FacultyName"
          id="FacultyName"
          className="px-3 py-2 text-xl rounded-lg border border-gray-200 focus:border-emerald-500 focus:outline-none "
        >
          {teachersList.map((teacher, index) => (
            <option key={index} value={teacher}>
              {teacher}
            </option>
          ))}
        </select>
      </div>
      <div className="mt-2 mb-4">
        <h3 className="text-lg font-semibold mb-2">Students:</h3>
        <ul className="space-y-1">
          {studentsList.map((student, index) => (
            <li key={index} className="flex items-center justify-between border-1 rounded-lg px-3 py-1">
              <span>{student.name} ({student.rollNumber})</span>
              <button
                className="ml-2 text-red-500 hover:text-red-700"
                onClick={() => handleRemoveStudent(student.id)}
                title="Remove student"
              >
                <MdOutlineDelete />
              </button>
            </li>
          ))}
        </ul>
      </div>
      <div className="flex gap-4 mt-auto">
        <button
          onClick={() => setEdit(!edit)}
          className="flex items-center px-4 py-2 rounded-lg bg-emerald-500 text-white hover:bg-emerald-600 transition"
        >
          {edit ? <MdOutlineSave /> : <MdOutlineEdit />}
        </button>
        <button
          className="flex items-center px-4 py-2 rounded-lg bg-red-500 text-white hover:bg-red-600 transition"
          onClick={handleDelete}
        >
          <MdOutlineDelete />
        </button>
      </div>
    </div>
  );
}


function CourseList() {
  const [data, setData] = useState([]);
  const [allStudents, setAllStudents] = useState([]);

  useEffect(() => {
    const fetchAll = async () => {
      const [coursesResp, studentsResp] = await Promise.all([
        api.get('/admin/course/all'),
        api.get('/admin/students/all'),
      ]);
      setData(coursesResp.data);
      setAllStudents(studentsResp.data);
    };
    fetchAll();
  }, []);

  // Handler to update course students (to be implemented for backend sync)
  const handleUpdateCourse = (updatedCourse) => {
    setData(prev => prev.map(c => c.id === updatedCourse.id ? updatedCourse : c));
    // TODO: Call backend to update course students
  };

  return (
    <div className="flex flex-col h-full max-w-1000 w-full">
      <div className="flex flex-col flex-none mb-5 w-full">
        <div className="w-full flex grow text-xl items-center border-1 border-accent-color-2 p-4 py-3 mx-0 rounded-2xl">
          <input
            type="text"
            placeholder="Search here!!"
            className="grow px-2 rounded-2xl outline-none text-3xl w-full"
          />
          <button type="submit" className="flex items-center hover:scale-110">
            <FaSearch />
          </button>
        </div>
      </div>
      <div className="h-full flex flex-nowrap max-w-11/12 overflow-x-auto w-full">
        {data.map((item, index) => (
          <CourseCard
            element={item}
            key={index}
            allStudents={allStudents}
            onUpdateCourse={handleUpdateCourse}
          />
        ))}
      </div>
    </div>
  );
}

export default CourseList;
