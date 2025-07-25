// StudentList.jsx
// Table of students with actions
import { useEffect, useState } from 'react';
import { FaSearch } from "react-icons/fa";
import { IoAddCircleOutline } from "react-icons/io5";
import ItemCard from './ItemCard';
import Alert from '../common/Alert';


function AddItem() {

}

function StudentList() {

  const [data, setData] = useState([]);
  const [addToggle, setAddToggle] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchStudents = async () => {
      try {
        const resp = await fetch("http://localhost:8080/admin/students/all");
        if (!resp.ok) {
          throw new Error(`Failed to fetch students (${resp.status} ${resp.statusText})`);
        }
        const res = await resp.json();
        setData(res.reverse());
        setError(null);
      } catch (e) {
        console.error(e);
        setError("Failed to load students. Please try again later.");
      }
    };
    fetchStudents();
  }, [])

  let newEle = { "rollNumber": "", "name": "", "email": "", "phoneNo": "" };

  const AddButtonArea = addToggle ? (
    <ItemCard
      element={newEle}
      key={-1}
      deleteItem={deleteItem}
      editValue={true}
      setData={setData}
      setAddToggle={setAddToggle}
      type_="students"
      setError={setError}
    />
  ) : (
    <div
      className="p-5 border-2 border-emerald-500 w-120 h-70 m-5 rounded-3xl 
               hover:shadow-2xl hover:scale-110 hover:shadow-emerald-500 
               flex items-center justify-center"
      onClick={() => setAddToggle(true)} // Add this to trigger add mode
    >
      <button className="text-5xl">
        <IoAddCircleOutline />
      </button>
    </div>
  );


  async function deleteItem(Id) {
    try {
      const url = `http://localhost:8080/admin/students/${Id}`;
      const respon = await fetch(url, { method: 'DELETE' });
      if (!respon.ok) {
        throw new Error(`Failed to delete student (${respon.status} ${respon.statusText})`);
      }
      // Update state immediately
      setData(prev => prev.filter(t => t["id"] !== Id));
      setError(null);
    } catch (err) {
      console.error(err);
      setError("Failed to delete student. Please try again later.");
    }
  }


  return (

    <div className="flex flex-col h-full">
      {error && (
        <Alert
          message={error}
          type="error"
          onClose={() => setError(null)}
        />
      )}

      <div className='flex  text-xl items-center border-1 border-accent-color-2 p-4 py-3 mx-3 rounded-2xl'>
        <input type="text" placeholder='Search here!!' className='grow px-2 rounded-2xl outline-none text-3xl' />
        <button type="submit" className='flex items-center hover:scale-110'>
          <FaSearch />
        </button>
      </div>

      <div className='Itemlist pt-9 flex flex-wrap justify-evenly flex-1 overflow-y-auto'>
        {AddButtonArea}

        {data.map((student, index) => (
          <ItemCard
            element={student}
            key={index}
            deleteItem={deleteItem}
            editValue={false}
            type_="students"
            setError={setError}
            setData={setData}
            setAddToggle={setAddToggle}
          />
        ))}
      </div>
    </div>
  );
};

export default StudentList;
