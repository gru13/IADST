// CourseList.jsx
// Table of courses and memberships
import React, { useEffect, useState } from "react";
import { FaSearch } from "react-icons/fa";
import { IoAddCircleOutline } from "react-icons/io5";
import { MdOutlineDelete, MdOutlineEdit, MdOutlineSave } from "react-icons/md";


function CourseCard({ element }) {
  const [item, setItem] = useState({ ...element });
  const [edit, setEdit] = useState(false);
  const [teachersList, setTeacherList] = useState([]);
  const [studentsList, setStudentsList] = useState([]);

  useEffect(() => {
    const getTeacherList = async () => {
      try {
        const response = await fetch('http://localhost:8080/admin/teachers/all/list/name');
        if (!response.ok) throw new Error('Failed to fetch teachers');
        const res = await response.json();
        setTeacherList(res);
      } catch (error) {
        alert(`Error fetching teachers: ${error.message}`);
      }
    };

    const getStudentsList = async () => {
      try {
        const response = await fetch(`http://localhost:8080/admin/course/${item.id}/students`);
        if (!response.ok) throw new Error('Failed to fetch student IDs');

        const studentIds = await response.json(); // list of student ID strings

        const results = await Promise.allSettled(
          studentIds.map(async (id) => {
            const res = await fetch(`http://localhost:8080/admin/students/${id}`);
            if (!res.ok) throw new Error(`Failed to fetch student ${id}`);
            return res.json();
          })
        );

        const successfulStudents = results
          .filter(result => result.status === 'fulfilled')
          .map(result => result.value);

        setStudentsList(successfulStudents);
        console.log('Loaded students:', successfulStudents);
      } catch (error) {
        alert(`Error fetching students list: ${error.message}`);
      }
    };


    getTeacherList();
    getStudentsList();
  }, [item.id]);

  const handleChanges = (e) => {
    setItem((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleDelete = () => { };


  const InputStyle = `px-2 py-2 grow outline-0 text-2xl ${edit ? 'underline underline-offset-6' : 'caret-transparent'}`

  return (
    <div className="p-5 h-11/12 group font-['fira_code']   rounded-2xl  border-2 m-2 flex">
      <div className="w-11/12">
        <input
          type="text"
          className={InputStyle}
          placeholder="Name"
          value={item.courseName}
          readOnly={edit}
          onChange={(e) => handleChanges(e)}
        />


        <select name="FacultyName" id="FacultyName">

          {teachersList.map((teacher, index) => (
            <option key={index} value={teacher}>
              {teacher}
            </option>
          ))}

        </select>

        <div className="mt-4">
          <h3 className="text-xl font-bold">Students:</h3>
          <ul>
            {studentsList.map((student, index) => (
              <li key={index} className="text-lg">
                {student.name} ({student.rollNumber})
              </li>
            ))}
          </ul>
        </div>
      </div>
      <div
        className={`text-3xl py-5 group-hover:block ${edit ? "justify-start" : "hidden"
          }`}
      >
        <button
          onClick={() => setEdit(!edit)}
          className="felx items-start hover:scale-110 hover:rotate-12 hover:text-emerald-500"
        >
          {edit ? <MdOutlineSave /> : <MdOutlineEdit />}
        </button>

        <button
          className="py-5 hover:text-red-500 hover:scale-110"
          onClick={() => handleDelete()}
        >
          <MdOutlineDelete />
        </button>
      </div>
    </div>
  );
}


function CourseList() {


  const [data, setData] = useState([]);


  useEffect(() => {
    const fetchCourse = async () => {
      const respons = await fetch('http://localhost:8080/admin/course/all', {
        method: "GET"
      })

      const result = await respons.json();
      setData(result);
    }
    fetchCourse();
  }
    , []
  )


  return (
    <div className="flex flex-col h-full max-w-1000">
      <div className="flex text-3xl items-center justify-between flex-none mb-5">
        <button>
          <IoAddCircleOutline />
        </button>

        <div className="flex grow text-xl items-center border-1 border-accent-color-2 p-4 py-3 mx-3 rounded-2xl">
          <input
            type="text"
            placeholder="Search here!!"
            className="grow px-2 rounded-2xl outline-none text-3xl"
          />
          <button type="submit" className="flex items-center hover:scale-110">
            <FaSearch />
          </button>
        </div>
      </div>

      <div className="h-full flex flex-nowrap max-w-11/12 overflow-x-scroll">
        {data.map((item, index) => <CourseCard element={item} key={index} />)}
      </div>
    </div>
  );
}

export default CourseList;
