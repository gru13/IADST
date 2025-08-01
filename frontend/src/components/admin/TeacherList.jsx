// TeacherList.jsx
import { useEffect, useState } from 'react';
import { FaSearch } from "react-icons/fa";
import { IoAddCircleOutline } from "react-icons/io5";
import ItemCard from './ItemCard';
import Alert from '../common/Alert';
import api from '../../api/axios';

function TeacherList() {
  const [data, setData] = useState([]);
  const [addToggle, setAddToggle] = useState(false);
  const [error, setError] = useState(null);

  // Initial fetch only when component mounts
  useEffect(() => {
    const fetchTeachers = async () => {
      try {
        const resp = await api.get("/admin/teachers/all"); // Use relative path, baseURL is set in axios.js
        const res = resp.data;
        setData(res.reverse()); 
        setError(null);
      } catch (e) {
        console.error(e);
        setError("Failed to load teachers. Please try again later.");
      }
    };
    fetchTeachers();
  }, [])


  let newEle = { "facultyId": "" , "name": "", "email": "", "phoneNo": "" };

  const AddButtonArea = addToggle ? (
    <ItemCard 
      element={newEle}
      key={-1}
      deleteItem={deleteItem}
      editValue={true}
      setData={setData}
      setAddToggle={setAddToggle}
      type_="teachers"
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
      const url = `/admin/teachers/${Id}`;
      const respon = await api.delete(url);
      // Update state immediately
      setData(prev => prev.filter(t => t["id"] !== Id));
      setError(null);
    } catch (err) {
      console.error(err);
      setError("Failed to delete teacher. Please try again later.");
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

      <div className='flex text-3xl items-center justify-between flex-none' >
        <div className='flex grow text-xl items-center border-1 border-accent-color-2 p-4 py-3 mx-3 rounded-2xl'>
          <input type="text" placeholder='Search here!!' className='grow px-2 rounded-2xl outline-none text-3xl' />
          <button type="submit" className='flex items-center hover:scale-110'>
            <FaSearch />
          </button>
        </div>
      </div>

      <div className='Itemlist pt-9 flex flex-wrap justify-evenly flex-1 overflow-y-auto'>

        {AddButtonArea}

        {data.map((teacher, index) => (
          <ItemCard 
            element={teacher}
            key={index}
            deleteItem={deleteItem}
            editValue={false}
            type_="teachers"
            setError={setError}
            setData={setData}
            setAddToggle={setAddToggle}
          />
        ))}
      </div>

    </div>
  );
}



export default TeacherList;
